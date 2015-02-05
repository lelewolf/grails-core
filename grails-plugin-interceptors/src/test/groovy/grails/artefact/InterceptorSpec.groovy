/*
 * Copyright 2014 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails.artefact

import grails.util.GrailsWebMockUtil
import org.grails.web.mapping.DefaultUrlMappingInfo
import org.grails.web.mapping.ForwardUrlMappingInfo
import org.grails.web.mapping.mvc.UrlMappingsHandlerMapping
import org.grails.web.servlet.mvc.GrailsWebRequest
import org.springframework.web.context.request.RequestContextHolder
import spock.lang.Specification



/**
 * @author graemerocher
 */
class InterceptorSpec extends Specification {

    void cleanup() {
        RequestContextHolder.setRequestAttributes(null)
    }

    void "Test the default interceptor mappings"() {
        given:"A test interceptor"
            def i = new TestInterceptor()
            def webRequest = GrailsWebMockUtil.bindMockWebRequest()

        when:"The current request is for a controller called test"
            webRequest.request.setAttribute(UrlMappingsHandlerMapping.MATCHED_REQUEST, new ForwardUrlMappingInfo(controllerName: "test"))
        then:"We match"
            i.doesMatch()

        when:"The current request is for a controller called test and action called bar"
            webRequest.request.setAttribute(UrlMappingsHandlerMapping.MATCHED_REQUEST, new ForwardUrlMappingInfo(controllerName: "test", actionName: "bar"))
        then:"We match"
            i.doesMatch()

        when:"The current request is for another controller"
            webRequest.request.setAttribute(UrlMappingsHandlerMapping.MATCHED_REQUEST, new ForwardUrlMappingInfo(controllerName: "other"))
        then:"We don't match"
            !i.doesMatch()
    }

    void "Test the match all interceptor mappings"() {
        given:"A test interceptor"
        def i = new Test3Interceptor()
        def webRequest = GrailsWebMockUtil.bindMockWebRequest()

        when:"The current request is for a controller called test"
        webRequest.request.setAttribute(UrlMappingsHandlerMapping.MATCHED_REQUEST, new ForwardUrlMappingInfo(controllerName: "test"))
        then:"We match"
        i.doesMatch()

        when:"The current request is for a controller called test and action called bar"
        webRequest.request.setAttribute(UrlMappingsHandlerMapping.MATCHED_REQUEST, new ForwardUrlMappingInfo(controllerName: "test", actionName: "bar"))
        then:"We match"
        i.doesMatch()

        when:"The current request is for another controller"
        webRequest.request.setAttribute(UrlMappingsHandlerMapping.MATCHED_REQUEST, new ForwardUrlMappingInfo(controllerName: "other"))
        then:"We match"
        i.doesMatch()

        when:"The current request is for an excluded controller controller"
        webRequest.request.setAttribute(UrlMappingsHandlerMapping.MATCHED_REQUEST, new ForwardUrlMappingInfo(controllerName: "foo"))
        then:"We don't match"
        !i.doesMatch()
    }

    void "Test the match specific controller interceptor mappings"() {
        given:"A test interceptor"
        def i = new Test2Interceptor()
        def webRequest = GrailsWebMockUtil.bindMockWebRequest()

        when:"The current request is for a controller called test"
        webRequest.request.setAttribute(UrlMappingsHandlerMapping.MATCHED_REQUEST, new ForwardUrlMappingInfo(controllerName: "foo"))
        then:"We match"
        i.doesMatch()

        when:"The current request is for a controller called test and action called bar"
        webRequest.request.setAttribute(UrlMappingsHandlerMapping.MATCHED_REQUEST, new ForwardUrlMappingInfo(controllerName: "foo", actionName: "bar"))
        then:"We don't match"
        !i.doesMatch()

        when:"The current request is for another controller action"
        webRequest.request.setAttribute(UrlMappingsHandlerMapping.MATCHED_REQUEST, new ForwardUrlMappingInfo(controllerName: "foo", actionName:"stuff"))
        then:"We match"
        i.doesMatch()

        when:"The current request is for another controller action and method"
        webRequest.request.setAttribute(UrlMappingsHandlerMapping.MATCHED_REQUEST, new ForwardUrlMappingInfo(controllerName: "foo", actionName:"stuff", httpMethod: "POST"))
        then:"We match"
        i.doesMatch()

    }
}

class TestInterceptor implements Interceptor {
    @Override
    boolean before() {
        return false
    }

    @Override
    boolean after() {
        return false
    }

    @Override
    void afterView(Throwable t) {

    }
}

class Test2Interceptor implements Interceptor {
    Test2Interceptor() {
        match(controller:"foo")
        .excludes(action:"bar")
    }
}

class Test3Interceptor implements Interceptor {
    Test3Interceptor() {
        matchAll()
        .excludes(controller:"foo")
    }
}