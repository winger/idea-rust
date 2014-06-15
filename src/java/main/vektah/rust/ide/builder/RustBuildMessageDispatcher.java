/*
 * Copyright 2000-2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vektah.rust.ide.builder;

import com.intellij.compiler.server.BuilderMessageHandler;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.containers.ConcurrentHashSet;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.api.CmdlineProtoUtil;
import org.jetbrains.jps.api.CmdlineRemoteProto;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@ChannelHandler.Sharable
class RustBuildMessageDispatcher {
  private static final Logger LOG = Logger.getInstance(RustBuildMessageDispatcher.class);

  private final Map<UUID, SessionData> myMessageHandlers = new ConcurrentHashMap<UUID, SessionData>(16, 0.75f, 1);
  private final Set<UUID> myCanceledSessions = new ConcurrentHashSet<UUID>();

  public void registerBuildMessageHandler(UUID sessionId,
                                          BuilderMessageHandler handler) {
    myMessageHandlers.put(sessionId, new SessionData(sessionId, handler));
  }

  @Nullable
  public BuilderMessageHandler unregisterBuildMessageHandler(UUID sessionId) {
    myCanceledSessions.remove(sessionId);
    final SessionData data = myMessageHandlers.remove(sessionId);
    return data != null? data.handler : null;
  }

  public void cancelSession(UUID sessionId) {
    if (myCanceledSessions.add(sessionId)) {
      final Channel channel = getConnectedChannel(sessionId);
      if (channel != null) {
        channel.writeAndFlush(CmdlineProtoUtil.toMessage(sessionId, CmdlineProtoUtil.createCancelCommand()));
      }
    }
  }

  @Nullable
  public Channel getConnectedChannel(final UUID sessionId) {
    final Channel channel = getAssociatedChannel(sessionId);
    return channel != null && channel.isActive()? channel : null;
  }

  @Nullable
  public Channel getAssociatedChannel(final UUID sessionId) {
    final SessionData data = myMessageHandlers.get(sessionId);
    return data != null? data.channel : null;
  }

  private static final class SessionData {
    final UUID sessionId;
    final BuilderMessageHandler handler;
    volatile Channel channel;

    private SessionData(UUID sessionId, BuilderMessageHandler handler) {
      this.sessionId = sessionId;
      this.handler = handler;
    }
  }
}
