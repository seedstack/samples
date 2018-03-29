#
# Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
#
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at http://mozilla.org/MPL/2.0/.
#

FROM java:alpine

EXPOSE 8080

COPY opt /opt
CMD exec java $JAVA_OPTS -jar /opt/bin/ddd-sample-capsule.jar
