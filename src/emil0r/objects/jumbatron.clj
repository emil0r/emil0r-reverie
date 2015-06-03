(ns emil0r.objects.jumbatron
  (:require [clojure.string :as str]
            [reverie.core :refer [defobject]]))


(defn jumbatron [request object {:keys [text period title tech css client]} params]
  [:div {:class (str "jumbatron " css)}
   [:div.title-background]
   [:h2 title]
   (if-not (str/blank? period)
     [:div.when [:span "Period"] period])
   (if-not (str/blank? client)
     [:div.client [:span "Client"] client])
   text
   (if-not (str/blank? tech)
     [:div.techs [:span "Techs"] tech])])


(defobject jumbatron
  {:table "objects_jumbatron"
   :migration {:path "src/emil0r/objects/migrations/jumbatron/"
               :automatic? true}
   :fields {:text {:initial ""
                   :type :richtext
                   :name "Text"}
            :period {:initial ""
                     :type :text
                     :name "Period"}
            :title {:initial ""
                    :type :text
                    :name "Title"}
            :tech {:initial ""
                   :type :text
                   :name "Techs used"}
            :css {:initial "project"
                  :type :dropdown
                  :options ["project"
                            "client"]
                  :name "CSS Class"}
            :client {:initial ""
                     :type :text
                     :name "Client"}}
   :sections [{:fields [:title :client :period :text :tech :css]}]}
  {:any jumbatron})
