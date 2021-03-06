/**
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.waveprotocol.box.common;

import org.waveprotocol.wave.model.document.operation.AnnotationBoundaryMap;
import org.waveprotocol.wave.model.document.operation.Attributes;
import org.waveprotocol.wave.model.document.operation.AttributesUpdate;
import org.waveprotocol.wave.model.document.operation.DocInitializationCursor;
import org.waveprotocol.wave.model.document.operation.DocOp;
import org.waveprotocol.wave.model.document.operation.DocOpCursor;
import org.waveprotocol.wave.model.document.operation.impl.InitializationCursorAdapter;
import org.waveprotocol.wave.model.wave.data.ReadableBlipData;
import org.waveprotocol.wave.model.wave.data.ReadableWaveletData;
import org.waveprotocol.wave.model.wave.data.WaveletData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility methods for rendering snippets.
 *
 * @author anorth@google.com (Alex North)
 */
public final class Snippets {

  /**
   * Concatenates all of the text for the given documents in
   * {@link WaveletData}.
   *
   * @param wavelet the wavelet for which to concatenate the documents.
   * @return A String containing the characters from all documents.
   */
  public static String collateTextForWavelet(ReadableWaveletData wavelet) {
    List<ReadableBlipData> documents = new ArrayList<ReadableBlipData>();
    for (String documentId : wavelet.getDocumentIds()) {
      documents.add(wavelet.getDocument(documentId));
    }
    return collateTextForDocuments(documents);
  }

  /**
   * Concatenates all of the text of the specified blips into a single String.
   *
   * @param documents the documents to concatenate.
   * @return A String containing the characters from all documents.
   */
  public static String collateTextForDocuments(Iterable<? extends ReadableBlipData> documents) {
    ArrayList<DocOp> docOps = new ArrayList<DocOp>();
    for (ReadableBlipData blipData : documents) {
      docOps.add(blipData.getContent().asOperation());
    }
    return collateTextForOps(docOps);
  }

  /**
   * Concatenates all of the text of the specified docops into a single String.
   *
   * @param documentops the document operations to concatenate.
   * @return A String containing the characters from the operations.
   */
  public static String collateTextForOps(Iterable<DocOp> documentops) {
    final StringBuilder resultBuilder = new StringBuilder();
    for (DocOp docOp : documentops) {
      docOp.apply(InitializationCursorAdapter.adapt(new DocOpCursor() {
        @Override
        public void characters(String s) {
          resultBuilder.append(s);
        }

        @Override
        public void annotationBoundary(AnnotationBoundaryMap map) {
        }

        @Override
        public void elementStart(String type, Attributes attrs) {
          if (type.equals(DocumentConstants.LINE)) {
            resultBuilder.append(" ");
          }
        }

        @Override
        public void elementEnd() {
        }

        @Override
        public void retain(int itemCount) {
        }

        @Override
        public void deleteCharacters(String chars) {
        }

        @Override
        public void deleteElementStart(String type, Attributes attrs) {
        }

        @Override
        public void deleteElementEnd() {
        }

        @Override
        public void replaceAttributes(Attributes oldAttrs, Attributes newAttrs) {
        }

        @Override
        public void updateAttributes(AttributesUpdate attrUpdate) {
        }
      }));
    }
    return resultBuilder.toString().trim();
  }

  /**
   * Returns a snippet or null.
   */
  public static String renderSnippet(final ReadableWaveletData wavelet,
      final int maxSnippetLength) {
    ReadableBlipData blip = wavelet.getDocument(DocumentConstants.CONVERSATION);
    if (blip == null) {
      // Render whatever data we have and hope its good enough
      return collateTextForWavelet(wavelet);
    }

    DocOp docOp = blip.getContent().asOperation();
    final StringBuilder sb = new StringBuilder();
    docOp.apply(InitializationCursorAdapter.adapt(new DocInitializationCursor() {
      @Override
      public void annotationBoundary(AnnotationBoundaryMap map) {
      }

      @Override
      public void characters(String chars) {
        // No chars in the conversation manifest
      }

      @Override
      public void elementEnd() {
      }

      @Override
      public void elementStart(String type, Attributes attrs) {
        if (sb.length() >= maxSnippetLength) {
          return;
        }

        if (DocumentConstants.BLIP.equals(type)) {
          String blipId = attrs.get(DocumentConstants.BLIP_ID);
          if (blipId != null) {
            ReadableBlipData document = wavelet.getDocument(blipId);
            if (document == null) {
              // We see this when a blip has been deleted
              return;
            }
            sb.append(collateTextForDocuments(Arrays.asList(document)));
            sb.append(" ");
          }
        }
      }
    }));
    return sb.toString();
  }

  private Snippets() {
  }
}
