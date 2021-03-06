/**
 * Copyright 2010 Google Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package org.waveprotocol.wave.model.account;


import org.waveprotocol.wave.model.util.Preconditions;
import org.waveprotocol.wave.model.wave.ParticipantId;

/**
 *
 */
class BasicAssignment implements MutableAssignment {
  private final ParticipantId participantId;
  private Role role;
  
  BasicAssignment(Assignment a) {
    this(a.getParticipant(), a.getRole());
  }
  
  BasicAssignment(ParticipantId id, Role role) {
    Preconditions.checkNotNull(id, "Null participant id");
    this.participantId = id;
    this.role = role;
  }
  
  @Override
  public ParticipantId getParticipant() {
    return participantId;
  }

  
  @Override
  public Role getRole() {
    return role;
  }
  
  @Override
  public void setRole(Role role) {
    this.role = role;
  }
}