package io.github.oliviercailloux.collaborative_exams.model.entity;

public enum Language {
	
	FRENCH, ENGLISH, SPANISH, ARABIC, ITALIAN;
	
	public static class ConvertLanguage 
	{ 
	    Language language;
	    
	    public static Language convertLanguage(String language)
	    {
	    	switch (language) 
	    	{
	    		case "FRENCH" :
	    			return Language.FRENCH;
	    			
	    		case "ENGLISH" :
	    			return Language.ENGLISH;
	    			
	    		case "SPANISH" :
	    			return Language.SPANISH;
	    			
	    		case "ARABIC" :
	    			return Language.ARABIC;
	    			
	    		case "ITALIAN" :
	    			return Language.ITALIAN;
	    		
	    	}
	    	//Par defaut la langue est en anglais
	    	return Language.ENGLISH;
	    }
	    
	}
}
