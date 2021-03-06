/* Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.api.codegen.util;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class CommonRenderingUtilTest {

  @Test
  public void testStripQuotes() {
    assertThat(CommonRenderingUtil.stripQuotes("\"abc\"")).isEqualTo("abc");
    assertThat(CommonRenderingUtil.stripQuotes("'abc'")).isEqualTo("abc");

    // Unbalanced quotes disables stripping.
    assertThat(CommonRenderingUtil.stripQuotes("\"abc")).isEqualTo("\"abc");
    assertThat(CommonRenderingUtil.stripQuotes("'abc")).isEqualTo("'abc");
    assertThat(CommonRenderingUtil.stripQuotes("abc\"")).isEqualTo("abc\"");
    assertThat(CommonRenderingUtil.stripQuotes("abc'")).isEqualTo("abc'");

    // Having quote character inside also disables stripping.
    // NOTE(pongad): I'm not sure why; keeping old behavior for now.
    assertThat(CommonRenderingUtil.stripQuotes("'a'bc'")).isEqualTo("'a'bc'");
    assertThat(CommonRenderingUtil.stripQuotes("\"a\"bc\"")).isEqualTo("\"a\"bc\"");
  }

  @Test
  public void testGetDocLines() {
    // Check that we don't care which form of line break is used.
    assertThat(CommonRenderingUtil.getDocLines("a\nb\nc")).containsExactly("a", "b", "c").inOrder();
    assertThat(CommonRenderingUtil.getDocLines("a\rb\rc")).containsExactly("a", "b", "c").inOrder();
    assertThat(CommonRenderingUtil.getDocLines("a\r\nb\r\nc"))
        .containsExactly("a", "b", "c")
        .inOrder();
    assertThat(CommonRenderingUtil.getDocLines("")).isEmpty();
  }
}
