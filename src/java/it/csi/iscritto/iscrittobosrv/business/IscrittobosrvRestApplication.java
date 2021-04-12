package it.csi.iscritto.iscrittobosrv.business;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import it.csi.iscritto.iscrittobosrv.business.be.impl.AllegatiApiServiceImpl;
import it.csi.iscritto.iscrittobosrv.business.be.impl.AutorizzazioneApiServiceImpl;
import it.csi.iscritto.iscrittobosrv.business.be.impl.DomandeApiServiceImpl;
import it.csi.iscritto.iscrittobosrv.business.be.impl.PingApiServiceImpl;
import it.csi.iscritto.iscrittobosrv.business.be.impl.ScuoleApiServiceImpl;
import it.csi.iscritto.iscrittobosrv.util.SpringInjectorInterceptor;
import it.csi.iscritto.iscrittobosrv.util.SpringSupportedResource;

@ApplicationPath("iscrittobosrvRest/be")
public class IscrittobosrvRestApplication extends Application{
	private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();
    public IscrittobosrvRestApplication(){
        singletons.add(new PingApiServiceImpl());
        singletons.add(new AutorizzazioneApiServiceImpl());
        singletons.add(new DomandeApiServiceImpl());
        singletons.add(new AllegatiApiServiceImpl());
        singletons.add(new ScuoleApiServiceImpl());
        singletons.add(new SpringInjectorInterceptor());
         
         for (Object c : singletons) {
 			if (c instanceof SpringSupportedResource) {
 				SpringApplicationContextHelper.registerRestEasyController(c);
 			}
 		}
    }
    @Override
    public Set<Class<?>> getClasses() {
    	HashSet<Class<?>> set = new HashSet<>();
    	// abilita la generazione del file yaml
    	/*
    			set.add(javax.annotation.Resource.class);
    			set.add(io.swagger.jaxrs.listing.ApiListingResource.class);
    			set.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
    			*/
         // return empty;
    			return set;
    }
    @Override
    public Set<Object> getSingletons() {
         return singletons;
    }

}

