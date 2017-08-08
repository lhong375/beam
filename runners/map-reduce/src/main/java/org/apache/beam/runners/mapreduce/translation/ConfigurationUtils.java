/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.beam.runners.mapreduce.translation;

import org.apache.beam.sdk.io.FileSystems;
import org.apache.beam.sdk.io.fs.ResolveOptions;
import org.apache.beam.sdk.io.fs.ResourceId;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Utilities to handle {@link Configuration}.
 */
public class ConfigurationUtils {

  public static ResourceId getResourceIdForOutput(String fileName, Configuration conf) {
    ResourceId outDir = FileSystems.matchNewResource(conf.get(FileOutputFormat.OUTDIR), true);
    return outDir.resolve(fileName, ResolveOptions.StandardResolveOptions.RESOLVE_FILE);
  }

  public static String getFileOutputDir(String baseFileOutputDir, int stageId) {
    if (baseFileOutputDir.endsWith("/")) {
      return String.format("%sstage-%d", baseFileOutputDir, stageId);
    } else {
      return String.format("%s/stage-%d", baseFileOutputDir, stageId);
    }
  }

  public static String getFileOutputPath(String baseFileOutputDir, int stageId, String fileName) {
    return String.format("%s/%s", getFileOutputDir(baseFileOutputDir, stageId), fileName);
  }

  public static String toFileName(String tagName) {
    return tagName.replaceAll("[^A-Za-z0-9]", "0");
  }

}
