/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package fm.last.darling.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fm.last.darling.nspace.Dimension;
import fm.last.darling.utils.Util;

public class UtilTest {

  @Test
  public void testEpochtoYMD() {
    String expected = "20090421";
    String ymd = Util.epochtoYMD(1240300000);
    assertEquals(ymd, expected);
  }

  @Test
  public void testreadRequestedProjections() throws FileNotFoundException {
    File testDataFolder = new File("src/darling/test/data");
    File yaml = new File(testDataFolder, "dataset.yaml");
    List<List<Dimension>> ret = Util.readRequestedProjections(yaml);

    List<List<Dimension>> expected = new ArrayList<List<Dimension>>();
    List<Dimension> projection0 = new ArrayList<Dimension>();
    projection0.add(new Dimension("country"));
    expected.add(projection0);
    List<Dimension> projection1 = new ArrayList<Dimension>();
    projection1.add(new Dimension("country"));
    projection1.add(new Dimension("service"));
    expected.add(projection1);

    assertEquals(ret.size(), expected.size());
    for (int i = 0; i < ret.size(); i++)
      assertEquals(ret.get(i).toString(), expected.get(i).toString());
  }

}
