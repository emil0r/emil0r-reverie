(ns emil0r.util
  (:require [clojure.string :as str]
            [ez-web.sidemenu :as sidemenu]
            [reverie.database :as db]
            [reverie.page :as page]))

(def img-class "img-responsive")

;; sidemenu
(defn get-title [page]
  (->> [(page/title page) (page/name page)]
       (remove str/blank?)
       first))

(defn sidemenu [db dev? uri page]
  (let [root-children (db/get-children db 1 (not dev?))
        root (->> root-children
                  (filter #(.startsWith (page/path page) (page/path %)))
                  first)
        children (db/get-children db (page/serial root) (not dev?))]
    (sidemenu/sidemenu uri (map (juxt page/path get-title) children) {:holder-attrib {:class "side-menu"}})))



;; top menu
(defn- menu-child [uri child]
  (let [attrib (if (re-find (re-pattern (str "^" (page/path child))) uri)
                 {:class :active})]
    [:li attrib
     [:a {:href (page/path child)} (get-title child)]]))


(defn menu [db dev? uri page]
  (let [root (db/get-page db 1 (not dev?))
        children (db/get-children db 1 (not dev?))
        mc (partial menu-child uri)]
    [:div.nav
     [:ul
      [:li (if (= uri (page/path root)) {:class :active} {})
       [:a {:href "/"} (get-title root)]]
      (map mc (remove #(-> % :properties :menu :hide?) children))]]))
