(defproject analytics "0.1.0-SNAPSHOT"
  :description "General purpose analytics engine in Clojure"
  :url "http://kuzu.io"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [migratus "0.8.13"]
                 [environ "1.0.2"]
                 [org.slf4j/slf4j-log4j12 "1.7.9"]
                 [mysql/mysql-connector-java "5.1.38"]
                 [conman "0.4.9"]
                 [org.clojure/core.async "0.2.374"]
                 [de.bertschneider/clj-geoip "0.2"]
                 [digest "1.4.4"]]
  :plugins [[migratus-lein "0.2.6"]]

  :main ^:skip-aot analytics.core

  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :resource-paths ["resources"])
