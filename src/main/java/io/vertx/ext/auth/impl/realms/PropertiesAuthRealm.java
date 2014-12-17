/*
 * Copyright 2014 Red Hat, Inc.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

package io.vertx.ext.auth.impl.realms;

import io.vertx.core.json.JsonObject;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.PropertiesRealm;
import static io.vertx.ext.auth.PropertiesAuthRealmConstants.*;

/**
 * TODO - move the constants out of here
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class PropertiesAuthRealm extends ShiroAuthRealmImpl {

  @Override
  public void init(JsonObject config) {
    this.config = config;
    PropertiesRealm propsRealm = new PropertiesRealm();
    String resourcePath = config.getString(PROPERTIES_PROPS_PATH_FIELD);
    if (resourcePath != null) {
      propsRealm.setResourcePath(resourcePath);
    } else {
      propsRealm.setResourcePath("classpath:vertx-users.properties");
    }
    propsRealm.init();
    this.securityManager = new DefaultSecurityManager(propsRealm);
    this.realm = propsRealm;
  }


}
