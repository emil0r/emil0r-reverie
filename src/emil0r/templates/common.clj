(ns emil0r.templates.common
  (:require [clojure.string :as str]
            [emil0r.util :as util]
            [hiccup.page :refer [include-css include-js]]
            [reverie.core :refer [area]]
            [reverie.database :as db]
            [reverie.downstream :as downstream]
            [reverie.page :as page]))

(defn og-property [property content]
  [:meta {:property property :content content}])

(defn head [page & [args]]
  (let [og-title (downstream/get :blog.og/title)
        og-description (downstream/get :blog.og/description)
        og-image (downstream/get :blog.og/image)
        title (cond
               (nil? (page/parent page)) (str (->> [og-title
                                                    (downstream/get :blog/title)
                                                    (page/title page)
                                                    (page/name page)]
                                                   (remove str/blank?)
                                                   first)
                                              " &mdash; emil0r")
               :else (str (->> [og-title
                                (downstream/get :blog/title)
                                (downstream/get :app-title)
                                (page/title page) (page/name page)]
                               (remove str/blank?)
                               first)
                          " &mdash; emil0r"))]
    [:head
     [:meta {:charset "UTF-8"}]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1, maximum-scale=1"}]
     [:link {:rel "shortcut icon" :href "/static/images/emil0r.png"}]
     [:title title]
     (if-not (str/blank? og-title)
       (og-property "og:title" og-title))
     (if-not (str/blank? og-description)
       (og-property "og:description" og-description))
     (if-not (str/blank? og-image)
       (og-property "og:image" (str "http://emil0r.com" og-image)))
     (map include-css ["/static/css/font-awesome.min.css"
                       "/static/css/main.css"])]))

(defn logo []
  [:div#logo
   [:a.no-pre {:href "/"}
    [:img {:src "/static/images/logo.png" :title "emil0r"}]]])

(defn sidemenu [db dev? uri page]
  (util/sidemenu db dev? uri page))

(defn nav [db dev? uri page]
  (util/menu db dev? uri page))

(defn ga-tag-manager [dev?]
  (if-not dev?
    "<noscript><iframe src='//www.googletagmanager.com/ns.html?id=GTM-KZ9XB6'
height='0' width='0' style='display:none;visibility:hidden'></iframe></noscript>
<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
'//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
})(window,document,'script','dataLayer','GTM-KZ9XB6');</script>"))


(defn footer [page dev?]
  (list
   [:footer.footer-container
    "Powered by " [:a {:href "http://reveriecms.org/"} "reverie/CMS"]]
   (map include-js ["/static/js/jquery-1.10.2.min.js"])
   (when dev?
     (map include-js ["/static/js/eyespy.js"
                      "/static/js/init.js"]))))
