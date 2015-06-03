(ns emil0r.core
    (:require [clojure.edn :as edn]
              [emil0r.command :as command]
              [emil0r.init :as init])
    (:gen-class))


(defn -main [& args]
  (if (= :command (-> args first edn/read-string))
    (command/run-command (map edn/read-string (rest args)))
    (init/init "settings.edn")))
