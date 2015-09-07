(defproject emil0r "0.1.0-SNAPSHOT"
  :description "My website"
  :url "http://emil0r.com"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [reverie-core "0.7.0-SNAPSHOT"]
                 [reverie-sql "0.7.0-SNAPSHOT"]
                 [reverie-batteries "0.3.0-SNAPSHOT"]
                 [reverie-blog "0.1.0-SNAPSHOT"]
                 [http-kit "2.1.19"]
                 [org.postgresql/postgresql "9.2-1002-jdbc4"]]
  :main emil0r.core
  :min-lein-version "2.0.0"
  :uberjar-name "emil0r.jar"
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[spyscope "0.1.5"]]
                   :injections [(require 'spyscope.core)
                                (use 'spyscope.repl)]}})
