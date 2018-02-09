package com.palmg.boot.webcore;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
		@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class)})
public class Application {}


/*@RestController
class Controller{

    @Autowired
    private HttpServletRequest request;
    
    @Autowired 
    private ApplicationConfig config;
    
    @Autowired
    private TestBean testBean;
    
    @RequestMapping("/greeting/{data}")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name, @PathVariable String data) {
    	//Enumeration<String> header = request.getHeaderNames();
    	List<Integer> ips = new ArrayList<Integer> ();
    	System.out.println("ABC");
    	for(int i = 0; i< 5; i++) {
    		ips.add(new Random().nextInt()); 
    	}
    	return "su";
    }
}

@Component
class ApplicationConfig{
	@Value("${key1}")
	private String key1;
}*/