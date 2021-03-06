/* Copyright (c) 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.wave.api.event;

import com.google.wave.api.Wavelet;
import com.google.wave.api.impl.EventMessageBundle;

/**
 * Event triggered when the tags on a wavelet change.
 */
public class WaveletTagsChangedEvent extends AbstractEvent {

  /**
   * Constructor.
   *
   * @param wavelet the wavelet where this event occurred.
   * @param bundle the message bundle this event belongs to.
   * @param modifiedBy the id of the participant that triggered this event.
   * @param timestamp the timestamp of this event.
   * @param blipId the id of the submitted blip.
   */
  public WaveletTagsChangedEvent(Wavelet wavelet, EventMessageBundle bundle, String modifiedBy,
      Long timestamp, String blipId) {
    super(EventType.WAVELET_TAGS_CHANGED, wavelet, bundle, modifiedBy, timestamp, blipId);
  }

  /**
   * Constructor for deserialization.
   */
  public WaveletTagsChangedEvent() {}

  /**
   * Helper method for type conversion.
   *
   * @return the concrete type of this event, or {@code null} if it is of a
   *     different event type.
   */
  public static WaveletTagsChangedEvent as(Event event) {
    if (!(event instanceof WaveletTagsChangedEvent)) {
      return null;
    }
    return WaveletTagsChangedEvent.class.cast(event);
  }
}
