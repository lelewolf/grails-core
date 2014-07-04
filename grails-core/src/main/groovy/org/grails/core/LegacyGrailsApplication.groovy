package org.grails.core

import grails.util.Metadata
import groovy.transform.CompileStatic
import org.codehaus.groovy.grails.commons.ArtefactHandler
import org.codehaus.groovy.grails.commons.ArtefactInfo
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.commons.GrailsClass
import org.springframework.context.ApplicationContext
import org.springframework.core.io.Resource

/**
 * Legacy bridge for older GrailsApplication API
 *
 * @author Graeme Rocher
 * @since 3.0
 */
@CompileStatic
class LegacyGrailsApplication implements GrailsApplication {

    grails.core.GrailsApplication grailsApplication

    LegacyGrailsApplication(grails.core.GrailsApplication grailsApplication) {
        this.grailsApplication = grailsApplication
    }

    @Override
    ConfigObject getConfig() {
        grailsApplication.config
    }

    @Override
    Map<String, Object> getFlatConfig() {
        grailsApplication.flatConfig
    }

    @Override
    ClassLoader getClassLoader() {
        grailsApplication.classLoader
    }

    @Override
    Class[] getAllClasses() {
        grailsApplication.allClasses
    }

    @Override
    Class[] getAllArtefacts() {
        grailsApplication.allArtefacts
    }

    @Override
    ApplicationContext getMainContext() {
        grailsApplication.mainContext
    }

    @Override
    void setMainContext(ApplicationContext context) {
        grailsApplication.mainContext = context
    }

    @Override
    ApplicationContext getParentContext() {
        grailsApplication.parentContext
    }

    @Override
    Class getClassForName(String className) {
        grailsApplication.getClassForName(className)
    }

    @Override
    void refreshConstraints() {
        grailsApplication.refreshConstraints()
    }

    @Override
    void refresh() {
        grailsApplication.refresh()
    }

    @Override
    void rebuild() {
        grailsApplication.rebuild()
    }

    @Override
    Resource getResourceForClass(Class theClazz) {
        grailsApplication.getResourceForClass(theClazz)
    }

    @Override
    boolean isArtefact(Class theClazz) {
        grailsApplication.isArtefact(theClazz)
    }

    @Override
    boolean isArtefactOfType(String artefactType, Class theClazz) {
        grailsApplication.isArtefactOfType(artefactType, theClazz)
    }

    @Override
    boolean isArtefactOfType(String artefactType, String className) {
        grailsApplication.isArtefactOfType(artefactType, className)
    }

    @Override
    GrailsClass getArtefact(String artefactType, String name) {
        return (GrailsClass)grailsApplication.getArtefact(artefactType, name)
    }

    @Override
    ArtefactHandler getArtefactType(Class theClass) {
        return (ArtefactHandler)grailsApplication.getArtefactType(theClass)
    }

    @Override
    ArtefactInfo getArtefactInfo(String artefactType) {
        return (ArtefactInfo)grailsApplication.getArtefactInfo(artefactType)
    }

    @Override
    GrailsClass[] getArtefacts(String artefactType) {
        return grailsApplication.getArtefacts(artefactType) as GrailsClass[]
    }

    @Override
    GrailsClass getArtefactForFeature(String artefactType, Object featureID) {
        return (GrailsClass)grailsApplication.getArtefactForFeature(artefactType, featureID)
    }

    @Override
    GrailsClass addArtefact(String artefactType, Class artefactClass) {
        return (GrailsClass)grailsApplication.addArtefact(artefactType, artefactClass)
    }

    @Override
    GrailsClass addArtefact(String artefactType, GrailsClass artefactGrailsClass) {
        return (GrailsClass)grailsApplication.addArtefact(artefactType, artefactGrailsClass)
    }

    @Override
    void registerArtefactHandler(ArtefactHandler handler) {
        grailsApplication.registerArtefactHandler(handler)
    }

    @Override
    boolean hasArtefactHandler(String type) {
        grailsApplication.hasArtefactHandler(type)
    }

    @Override
    ArtefactHandler[] getArtefactHandlers() {
        return grailsApplication.artefactHandlers as ArtefactHandler[]
    }

    @Override
    void initialise() {
        grailsApplication.initialise()
    }

    @Override
    boolean isInitialised() {
        grailsApplication.isInitialised()
    }

    @Override
    Metadata getMetadata() {
        grailsApplication.metadata
    }

    @Override
    GrailsClass getArtefactByLogicalPropertyName(String type, String logicalName) {
        return (GrailsClass)grailsApplication.getArtefactByLogicalPropertyName(type, logicalName)
    }

    @Override
    void addArtefact(Class artefact) {
        grailsApplication.addArtefact(artefact)
    }

    @Override
    boolean isWarDeployed() {
        grailsApplication.isWarDeployed()
    }

    @Override
    void addOverridableArtefact(Class artefact) {
        grailsApplication.addOverridableArtefact(artefact)
    }

    @Override
    void configChanged() {
        grailsApplication.configChanged()
    }

    @Override
    ArtefactHandler getArtefactHandler(String type) {
        return (ArtefactHandler)grailsApplication.getArtefactHandler(type)
    }
}