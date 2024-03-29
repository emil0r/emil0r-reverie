(ns emil0r.dev
  (:require [joplin.core :as joplin]
            joplin.jdbc.database
            [emil0r.init :as init]))

(comment


  ;; run this for running the server in dev mode through the REPL
  (do
    (init/stop)
    (init/init "settings.edn"))


  ;; copy/paste and change the commented out migration to suit your needs
  ;; run in the REPL as necessary during development
  (let [mmaps (map (fn [[table path]]
                     {:db {:type :sql
                           :migration-table table
                           :url (str "jdbc:postgresql:"
                                     "//localhost:5432/emil0r"
                                     "?user=" "emil0r"
                                     "&password=" "foobarbaz")}
                      :migrator path})
                   (array-map
                    ;;"migrations_<name-here>" "src/emil0r/objects/migrations/<name-here>/"
                    ;;"migrations" "resources/migrations/postgresql"
                    ;;"migrations_jumbatron" "src/emil0r/objects/migrations/jumbatron/"
                    "migrations_module_reverie_blog" "resources/migrations/modules/blog/"
                    ))]
    ;; IMPORTANT NOTE: this has destructive side effects in the sense
    ;; of wiping out previously applied migrations.
    ;; due to the nature of the weak binding between objects and the table
    ;; keeping track of them this can cause problems with reverie trying
    ;; to pull in objects that no longer exists. to fix that you would need
    ;; to manually delete the reference for that object in the reverie_object
    ;; table
    (doseq [mmap mmaps]
      (joplin/rollback-db mmap 1)
      (joplin/migrate-db mmap)))

  )
