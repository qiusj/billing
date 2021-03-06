/*
 jBilling - The Enterprise Open Source Billing System
 Copyright (C) 2003-2011 Enterprise jBilling Software Ltd. and Emiliano Conde

 This file is part of jbilling.

 jbilling is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 jbilling is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with jbilling.  If not, see <http://www.gnu.org/licenses/>.

 This source was modified by Web Data Technologies LLP (www.webdatatechnologies.in) since 15 Nov 2015.
 You may download the latest source from webdataconsulting.github.io.

 */

package com.sapienter.jbilling.server.util.api;

import com.sapienter.jbilling.common.FormatLogger;
import com.sapienter.jbilling.server.util.NanoStopWatch;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author Vikas Bodani
 * @since Dec 12, 2011
 * Logging aspect that uses NanoStopWatch to calculate time elapsed in nano-seconds
 */
public class APIStopWatchAspect implements MethodBeforeAdvice, AfterReturningAdvice {

    private static final FormatLogger LOG   = new FormatLogger(APIStopWatchAspect.class);

    private NanoStopWatch stopWatch= null;
    
    public NanoStopWatch getStopWatch() {
        return stopWatch;
    }

    public void setStopWatch(NanoStopWatch stopWatch) {
        this.stopWatch = stopWatch;
    }

    public void before(Method method, Object[] args, Object target) throws Throwable {
        stopWatch.setName(method.getName());
        stopWatch.start();
    }

    public void afterReturning(Object ret, Method method, Object[] args, Object target) throws Throwable {
        stopWatch.stop();
        LOG.debug("%s.%s took: %s ms.", target.getClass().getSimpleName(), stopWatch.getName(), stopWatch.getElapsedMilliseconds());
    }
}
