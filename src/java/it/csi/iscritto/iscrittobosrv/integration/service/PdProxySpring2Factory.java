package it.csi.iscritto.iscrittobosrv.integration.service;

import it.csi.csi.porte.InfoPortaDelegata;
import it.csi.csi.porte.proxy.PDProxy;
import it.csi.csi.util.xml.PDConfigReader;
import it.csi.iscritto.iscrittobosrv.util.Constants;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

/**
 * Factory per servizi Porta Delegata
 *
 * @author 71740
 */
@SuppressWarnings("rawtypes")
public class PdProxySpring2Factory implements FactoryBean, InitializingBean {

    protected static final Logger log =  Logger.getLogger(Constants.COMPONENT_NAME + ".business");

    /**
     * Oggetto restituito
     */
    private Object pdInterface = null;

    /**
     * File di configurazione della Porta Delegata
     */
    private Resource pdConfigXml = null;

    /**
     * Definizione del tipo di oggetto restituito
     */
    private String interfaceClass = null;

    /**
     * Costruttore vuoto
     *
     */
    public PdProxySpring2Factory() {
        log.debug("[PdProxySpring2Factory::PdProxySpring2Factory] Costruttore vuoto");
    }

    /**
     * Costruttore completo
     * @param pdConfigXml File di configurazione della porta delegata
     * @param interfaceClass Tipo di classe/interfaccia restituita
     * @throws Exception
     */
    public static Object getPdProxy(Resource pdConfigXml, String interfaceClass) throws Exception {
        log.debug("[PdProxySpring2Factory::getPdProxy] BEGIN");

        PdProxySpring2Factory fac = new PdProxySpring2Factory();

        fac.pdConfigXml = pdConfigXml;
        log.info("[PdProxySpring2Factory::getPdProxy] Property 'pdConfigXml' = '" + pdConfigXml + "'");

        fac.interfaceClass = interfaceClass;
        log.info("[PdProxySpring2Factory::getPdProxy] Property 'interfaceClass' = '" + interfaceClass + "'");

        log.debug("[PdProxySpring2Factory::getPdProxy] END");

        return fac.getObject();
    }

    /**
     *
     * @param pdConfigXml
     */
    public void setPdConfigXml(Resource pdConfigXml) {
        log.debug("[PdProxySpring2Factory::setPdConfigXml] BEGIN");

        this.pdConfigXml = pdConfigXml;
        log.info("[PdProxySpring2Factory::setPdConfigXml] Property 'pdConfigXml' = '" + pdConfigXml + "'");

        log.debug("[PdProxySpring2Factory::setPdConfigXml] END");
    }

    public void setInterfaceClass(String interfaceClass) {
        log.debug("[PdProxySpring2Factory::setInterfaceClass] BEGIN");

        this.interfaceClass = interfaceClass;
        log.info("[PdProxySpring2Factory::setInterfaceClass] Property 'interfaceClass' = '" + interfaceClass + "'");

        log.debug("[PdProxySpring2Factory::setInterfaceClass] END");
    }

    // --- FactoryBean interface ---

    /*
     * (non-Javadoc)
     * @see org.springframework.beans.factory.FactoryBean#getObject()
     */
    public Object getObject() throws Exception {
        log.debug("[PdProxySpring2Factory::getObject] BEGIN");

        InfoPortaDelegata info = null;
        info = PDConfigReader.read(pdConfigXml.getInputStream());
        pdInterface = PDProxy.newInstance(info);

        log.debug("[PdProxySpring2Factory::getObject] END");

        return pdInterface;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.beans.factory.FactoryBean#getObjectType()
     */
    public Class<?> getObjectType() {
        log.debug("[PdProxySpring2Factory::getObjectType] BEGIN");

        Class<?> currClass = null;
        if (interfaceClass != null) {
            try {
                currClass = Class.forName(interfaceClass);
            } catch (ClassNotFoundException e) {
                log.error("[PdProxySpring2Factory::getObjectType] Errore: " + e.getMessage(), e);
            }
        }

        log.debug("[PdProxySpring2Factory::getObjectType] END");

        return currClass;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.beans.factory.FactoryBean#isSingleton()
     */
    public boolean isSingleton() {
        return true;
    }

    // --- InitializingBean interface ---

    /*
     * (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        log.debug("[PdProxySpring2Factory::afterPropertiesSet] BEGIN");

        if (pdConfigXml == null) {
            log.error("[PdProxySpring2Factory::afterPropertiesSet] Property 'pdConfigXml' non impostata");

            throw new IllegalArgumentException("Property 'pdConfigXml' non impostata");
        }

        if (interfaceClass == null) {
            log.error("[PdProxySpring2Factory::afterPropertiesSet] Property 'interfaceClass' non impostata");

            throw new IllegalArgumentException("Property 'interfaceClass' non impostata");
        }

        InfoPortaDelegata info = null;
        info = PDConfigReader.read(pdConfigXml.getInputStream());
        pdInterface = PDProxy.newInstance(info);

        log.debug("[PdProxySpring2Factory::afterPropertiesSet] END");
    }

}
