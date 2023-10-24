package lv.venta.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import lv.venta.models.users.AcademicPersonel;
import lv.venta.models.users.Student;

class ThesisTest {

	/*Thesis t1 = new Thesis();
	Thesis t2 = new Thesis(
            "Datoru tīklu drošības risinājumu analīze un izvērtējums",
            "Analysis and evaluation of computer network security solutions",
            Arrays.asList(Area.security, Area.network), 
            Complexity.average,
            "Kvalifikācijas darba mērķis ir izpētīt un analizēt dažādus datoru tīklu drošības risinājumus, to efektivitāti un piemērošanas jomas. Darba gaitā tiks veikta salīdzinoša analīze starp dažādiem drošības protokoliem, mehānismiem un algoritmiem, lai noskaidrotu to atbilstību datoru tīklu drošības prasībām. Galvenais uzdevums ir izvērtēt esošos risinājumus un sniegt ieteikumus par to piemērošanu un uzlabošanu.",
            new AcademicPersonel());
	  Thesis t3 = new Thesis(
	            "A",      
	            "B",            
	            null,                
	            null,             
	            "This is a very long private note that exceeds the maximum length allowed by validation.This is a very long private note that exceeds the maximum length allowed by validation.This is a very long private note that exceeds the maximum length allowed by validation.This is a very long private note that exceeds the maximum length allowed by validation.This is a very long private note that exceeds the maximum length allowed by validation.This is a very long private note that exceeds the maximum length allowed by validation.This is a very long private note that exceeds the maximum length allowed by validation.This is a very long private note that exceeds the maximum length allowed by validation.This is a very long private note that exceeds the maximum length allowed by validation.This is a very long private note that exceeds the maximum length allowed by validation.This is a very long private note that exceeds the maximum length allowed by validation.This is a very long private note that exceeds the maximum length allowed by validation.This is a very long private note that exceeds the maximum length allowed by validation.This is a very long private note that exceeds the maximum length allowed by validation.This is a very long private note that exceeds the maximum length allowed by validation.",  
	            null);
	Thesis t4 = new Thesis(null, null, null, null, null, null);

	@Test
	public void testDefaultThesis() {
		assertEquals("",t1.getTitleLv());
		assertEquals("",t1.getTitleEn());
		assertEquals(0, t1.getApplications());
		assertTrue(t1.getAreas().isEmpty());
		assertNull(t1.getComplexity());
		assertEquals("",t1.getPrivateNotes());
		assertEquals("",t1.getPublicNotes());
		assertNull(t1.getAssignedStudent());
		assertNull(t1.getSupervisor());
		assertTrue(t1.getReviewers().isEmpty());
		assertTrue(t1.getThesisApplications().isEmpty());

	}

	@Test
	public void testGoodThesis() {
		assertEquals("Datoru tīklu drošības risinājumu analīze un izvērtējums", t2.getTitleLv());
        assertEquals("Analysis and evaluation of computer network security solutions", t2.getTitleEn());
        assertEquals(2, t2.getAreas().size());
        assertEquals(Complexity.average, t2.getComplexity());
        assertEquals("Kvalifikācijas darba mērķis ir izpētīt un analizēt dažādus datoru tīklu drošības risinājumus, to efektivitāti un piemērošanas jomas. Darba gaitā tiks veikta salīdzinoša analīze starp dažādiem drošības protokoliem, mehānismiem un algoritmiem, lai noskaidrotu to atbilstību datoru tīklu drošības prasībām. Galvenais uzdevums ir izvērtēt esošos risinājumus un sniegt ieteikumus par to piemērošanu un uzlabošanu.", t2.getPublicNotes());
        assertNotNull(t2.getSupervisor());
        assertNull(t2.getAssignedStudent());
        assertNull(t2.getThesisApplications());
		assertNull(t2.getAssignedStudent());
		assertNull(t2.getPrivateNotes());
	}

	@Test
	public void testBadThesis() {
		assertEquals("", t3.getTitleLv());
	    assertEquals("", t3.getTitleEn());
	    assertNull(t3.getAreas().size());
		assertNull(t3.getComplexity());
		assertEquals("",t3.getPrivateNotes());
	    assertNull(t3.getSupervisor());
	}		

	
	@Test
	public void testNullThesis() {
		
	}*/

}
