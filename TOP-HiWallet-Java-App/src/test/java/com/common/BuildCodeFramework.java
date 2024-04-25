package com.common;

import java.io.IOException;

import com.base.core.devtools.utils.BuildCodeTemplates;

/**
 * idea下设置Working directory为$MODULE_WORKING_DIR$
 * @author start
 *
 */
public class BuildCodeFramework {

    public static void main(String[] args) throws IOException {
        BuildCodeTemplates buildCodeTemplates = new BuildCodeTemplates("com/topnetwork", "validator", "Version");
        buildCodeTemplates.buildCodeFile();
    }

}