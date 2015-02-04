(ns relajo.notes)

#_(ns relajo.releases
  (:require [reagent.core :as reagent :refer [atom]]
            [ajax.core :refer [GET]]))

#_(swap! releases-atom
       #(update-in releases [0 :version] (constantly "SS")))

#_(sort-by :version > releases)

#_(update-in releases [0 :version] (constantly "A"))

#_(defn error-handler [{:keys [status status-text]}]
  (.log js/console (str "Something went wrong: " status " " status-text)))

#_(defn fetch []
  (GET "http://localhost:7777/releases"
       {:handler (fn [response]
                   (reset! releases-atom response))
        :error-handler error-handler}))

#_(defn normalize-message [msg kca]
  (-> msg
      str/split-lines
      first
      (str/replace-first (re-pattern (str kca "\\s*-*:*")) " ")))


#_[:span {:onMouseOver #(js/alert sha)} (normalize-message message kca)]

