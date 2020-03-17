/*
 * Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.camel;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.Logging;
import org.seedstack.seed.testing.junit4.SeedITRunner;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@RunWith(SeedITRunner.class)
public class FileCopyRouteIT {
    private static final String DATA_CONTENT="Camel Addon data content";
    @Logging
    private Logger logger;

    @Configuration("sample.folder.origin")
    private File originFolder;
    @Configuration("sample.folder.destination")
    private File destinationFolder;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    /**
     * The file copy route is defined in {@link org.seedstack.samples.camel.routes.FileCopyRouteBuilder}
     * and parametrized with configuration values.
     * Seedstack adds this route builder to the Camel context and starts it.<br>
     * <br>
     * This test copies a file to the origin folder and check that a copy of this file is present in the destination folder
     */
    @Test
    public void testFileCopyRoute() throws IOException, InterruptedException {
        cleanTestDirectories();
        File dataFile=createAndFillDataFile();

        try {
            //Copy the file to the origin folder
            logger.info("Copying file to the origin folder");
            FileUtils.copyFileToDirectory(dataFile, originFolder);
            //Wait a little for the Camel route to proceed its behaviour
            Thread.sleep(1000);
            File copiedFile = FileUtils.getFile(destinationFolder, dataFile.getName());
            Assert.assertTrue("The destination file does not exist", copiedFile.exists());
            logger.info("The file is copied to the destination folder");
            Assert.assertTrue("The data of origin and destination files do not match !", FileUtils.contentEquals(dataFile, copiedFile));
            logger.info("The file content has been copied");
        }
        finally {
            cleanTestDirectories();
        }
    }
    private File createAndFillDataFile(){
        logger.info("Creating data file");
        String fileName="CamelFileRouteData"+System.currentTimeMillis();
        File dataFile=null;
        try {
            dataFile=tempFolder.newFile(fileName);
            FileUtils.write(dataFile, DATA_CONTENT, Charset.defaultCharset());
        }
        catch (IOException ioe){
            Assert.fail("IOException while creating and filling data file");
        }
        return dataFile;
    }

    private void cleanTestDirectories(){
        logger.info("Cleaning test directories");
        try {
            FileUtils.cleanDirectory(originFolder);
            FileUtils.cleanDirectory(destinationFolder);
        }
        catch(IOException ioe){
            Assert.fail("IOException while cleaning test directories");
        }
    }
}
