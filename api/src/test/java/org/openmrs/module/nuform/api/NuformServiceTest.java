/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.nuform.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.nuform.Nuform;
import org.openmrs.module.nuform.NuformDef;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Tests
 */
public class  NuformServiceTest extends BaseModuleContextSensitiveTest {
    private NuformService nuformService;

    @Before
    public void setUp() {
        nuformService = Context.getService(NuformService.class);
        try {
            executeDataSet("NuformTestDataSet.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void shouldSetupContext() {
		assertNotNull(Context.getService(NuformService.class));
	}


    @Test
    public void shouldReturnAllDefs() {
        List<NuformDef> nuformDefs = nuformService.getAllDef("");
        assertNotNull(nuformDefs);
    }


    @Test
    public void shouldReturnAllNuforms() {
        List<Nuform> nuform = nuformService.getAllNuforms("");
        assertNotNull(nuform);
    }

    @Test
    public void shouldGetNuformById() {
        Nuform nuform = nuformService.getNuformById(2);
        assertEquals("Id of Nuform returned:", nuform.getId().toString(), "2");
    }

    @Test
    public void shouldGetNuformDefById() {
        NuformDef nuformDef = nuformService.getNuformDefById(1);
        assertEquals("Id of Nuform returned:", nuformDef.getId().toString(), "1");
    }

    @Test
    public void shouldReturnNuformsByDef() {
        NuformDef nuformDef = nuformService.getNuformDefById(1);
        List<Nuform> nuform = nuformService.getAllNuformsByDef(nuformDef);
        assertEquals("Should return two nuforms: ", 2, nuform.size());
    }

    @Test
    public void shouldSaveNuform() {
        Nuform nuform = nuformService.getNuformById(2);
        nuform.setLesionmap("Test");
        assertNotNull(nuformService.saveNuform(nuform));
    }

    @Test
    public void shouldSaveNuformDef() {
        NuformDef nuformDef = nuformService.getNuformDefById(1);
        nuformDef.setBackgroundImage("Default.PNG");
        assertNotNull(nuformService.saveNuformDef(nuformDef));
    }
}
