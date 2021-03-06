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
package fm.last.darling.nspace;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

// handles projections, obviously.
public class Projectionist {
  public static Projection dimensionality_reduction(ArrayList<Dimension> requested, Projection dimensions) {
    Projection projection = new Projection();
    for (Dimension d : requested)
      projection.put(d, dimensions.get(d));
    return projection;
  }

  public static TreeMap<String, String> dimensionality_reduction(List<Dimension> requested,
      TreeMap<String, String> dimensions) {
    TreeMap<String, String> projection = new TreeMap<String, String>();
    for (Dimension d : requested)
      projection.put(d.toString(), dimensions.get(d.toString()));
    return projection;
  }
}
