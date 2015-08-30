(ns emil0r.templates.blog
  (:require [hiccup.core :refer [html]]
            [hiccup.page :refer [html5]]
            [emil0r.templates.common :as common]
            [emil0r.util :as util]
            [reverie.core :refer [deftemplate area]]
            [reverie.downstream :as downstream]))


(defn template-blog [{:keys [uri] :as request} page properties params]
  (downstream/assoc! :reverie.image/class util/img-class)
  (let [{:keys [edit? database dev?]} (get-in request [:reverie])
        area-entry (html (area entry))
        area-entries (html (area entries))
        area-pagination (html (area pagination))
        area-latest (html (area latest))
        area-categories (html (area categories))]

    (html5
     (common/head page)
     [:body
      (common/ga-tag-manager dev?)
      [:div#content.container
       (common/logo)
       (common/nav database dev? uri page)
       [:div.container.blog
        [:div.row
         [:div.col-md-9
          [:div.blog-title [:span "("] "selfmindead" [:span.blog " :thoughts"] [:span ")"]]
          area-entry
          area-entries]
         [:div.col-md-3
          [:a {:href "/feed.atom"}
           [:img.feed {:src "/static/images/feed.png"}]]
          area-latest
          area-categories]]
        [:div.row
         [:div.col-md-12
          area-pagination]]]
       (common/footer page dev?)]])))


(deftemplate blog template-blog)
