(ns relajo.app
  (:require [reagent.core :as reagent :refer [atom]]
            [relajo.releases :as releases]
            [relajo.util :as u]
            [clojure.string :as str]))

(defn greeting []
  [:div.page-header
    [:h1 "I am your daily release buddy"]])

(defn footer []
  [:footer.footer
    [:div.container
     [:p.text-muted "X deployments to Prod so far and counting :)"]]])

(def commit-state->label-class {:in-progress "label-warning" :dev-complete "label-info" :resolved "label-success"})

(defn commit-state [state kca]
  [:a {:href (str "https://jira.corp.ebay.com/browse/" kca) :target "_blank"}
   [:span {:class (str "label " (get commit-state->label-class state "label-default"))} kca]])

(defn norm-message [message kca]
  (-> message
      str/split-lines
      first
      (str/replace-first (re-pattern (str kca "\\s*-*:*")) " ")))

;; !!! strike out commit text  when kca is resolved
(defn commit-info [{:keys [sha author message kca state html-url]}]
  [:div.media
   [:div.media-left
    [:div
     [:a {:href html-url :target "_blank"}
      [:span.label.label-default author]]]]
   [:div.media-body
    [commit-state state kca]
    (norm-message message kca)]])

(defn release-state->label-class [{:keys [state commits]}]
  (cond
   (pos? (u/count-in commits :state :in-progress)) (get commit-state->label-class :in-progress "label-default")
   (pos? (u/count-in commits :state :dev-complete)) (get commit-state->label-class :dev-complete "label-default")
   (pos? (u/count-in commits :state :resolved)) (get commit-state->label-class :resolved "label-default")))

(defn release-state [{:keys [state version deployments] :as r}]
  [:div {:class "media-left media-middle"}
   [:h1 [:span {:class (str "label " (release-state->label-class r))} version]]
   (for [d deployments]
     ^{:key d} [:span.text-success.with-padding [:b (-> d :environment str/upper-case)]])])

(defn release-info [{:keys [version state commits] :as r}]
  [:div
   [:li.media
    [release-state r]
    [:div.media-body
     [:div {:class "btn-group btn-group-xs" :role "group"}
      [:button {:class "btn btn-warning" :type "button"}
       "In Progress " [:span.badge (u/count-in commits :state :in-progress)]]
      [:button {:class "btn btn-info" :type "button"}
       "Dev Complete " [:span.badge (u/count-in commits :state :dev-complete)]]
      [:button {:class "btn btn-success" :type "button"}
       "Resolved " [:span.badge (u/count-in commits :state :resolved)]]]
     (for [c commits]
       ^{:key c}[commit-info c])]]
   [:hr]])

(defn release-buddy [releases]
  [:div
   [:div.container
    [greeting]
    [:ul.media-list
     (for [r @releases]
       ^{:key r} [release-info r])]]
   [footer]])

(defn ^:export main []
  (reagent/render-component [release-buddy (releases/all)]
                            (.getElementById js/document "app"))
  (releases/fetch))
