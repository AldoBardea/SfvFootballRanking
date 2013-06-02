package ch.keo.sfvfootballranking.model;

public class Regions {
    
    private String[] regions = new String[]{"/afv/de","/fvbj/de","/ifv/de","/fvnws/de","/ofv/de","/sofv/de",
	    "/fvrz/de","/ftc/de","/aff/de","/acgf/de","/anf/de","/acvf/de","/avf/de"};
    private String region = null;
    
    public Regions() {
		
    }
    
    public String getRegion(int index) {
	region = regions[index];
        return region;
    }
}