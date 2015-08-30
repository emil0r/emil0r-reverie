(ns emil0r.templates.main
  (:require [hiccup.page :refer [html5]]
            [emil0r.templates.common :as common]
            [emil0r.util :as util]
            [reverie.core :refer [deftemplate area]]
            [reverie.downstream :as downstream]))

(defn template [{:keys [uri] :as request} page properties params]
  (let [{:keys [edit? database dev?]} (get-in request [:reverie])]
    (downstream/assoc! :reverie.image/class util/img-class)
    (html5
     (common/head page)
     [:body
      (common/ga-tag-manager dev?)
      [:div#content.container
       (common/logo)
       (common/nav database dev? uri page)
       [:div.container
        [:div.row
         [:div.col-md-12
          (area a)]]]
       (common/footer page dev?)]])))

(defn template-sidemenu [{:keys [uri] :as request} page properties params]
  (let [{:keys [edit? database dev?]} (get-in request [:reverie])]
    (downstream/assoc! :reverie.image/class util/img-class)
    (html5
     (common/head page)
     [:body
      (common/ga-tag-manager dev?)
      [:div#content.container
       (common/logo)
       (common/nav database dev? uri page)
       [:div.container
        [:div.row
         [:div.col-md-2
          (util/sidemenu database dev? uri page)]
         [:div.col-md-10
          (area a)]]]
       (common/footer page dev?)]])))


(deftemplate main template)
(deftemplate main-sidemenu template-sidemenu)
