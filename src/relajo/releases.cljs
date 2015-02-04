(ns relajo.releases
  (:require [reagent.core :as reagent :refer [atom]]))

(def releases
  [{:version "2000.1.1"
    :build-time #inst "2015-01-01"
    :creation-time #inst "2015-01-01"
    :commits [{:sha "11179467cafc13925eb18ddf0671e0c32949763d"
               :author "John Doe" :message "added security" :kca "KCA-11111" :state :resolved
               :merge-pull-request? false
               :commit-time #inst "2015-01-02"
               :html-url "http://www.google.com"
               :creation-time #inst "2015-01-01"}
              {:sha "22279467cafc13925eb18ddf0671e0c32949763d"
               :author "Jane Doe" :message "Nothing to see here, move along" :kca "KCA-22222" :state :resolved
               :merge-pull-request? false
               :commit-time #inst "2015-01-03"
               :html-url "http://www.google.com"
               :creation-time #inst "2015-01-01"}
              {:sha "33379467cafc13925eb18ddf0671e0c32949763d"
               :author "Jane Doe" :message "commented out failing tests" :kca "KCA-33333" :state :resolved
               :merge-pull-request? false
               :commit-time #inst "2015-01-04"
               :html-url "http://www.google.com"
               :creation-time #inst "2015-01-01"}]
    :deployments [{:environment "UAT"
                   :deployment-time #inst "2015-01-02" :creation-time #inst "2015-01-01"}
                  {:environment "PROD"
                   :deployment-time #inst "2015-01-02" :creation-time #inst "2015-01-01"}]}
   {:version "2000.1.2"
    :build-time #inst "2015-01-01"
    :creation-time #inst "2015-01-01"
    :commits [{:sha "01179467cafc13925eb18ddf0671e0c32949763d"
               :author "John Doe" :message "Well the book was obviously wrong" :kca "KCA-44444" :state :dev-complete
               :merge-pull-request? false
               :commit-time #inst "2015-02-01"
               :html-url "http://www.google.com"
               :creation-time #inst "2015-01-01"}
              {:sha "02279467cafc13925eb18ddf0671e0c32949763d"
               :author "Jane Doe" :message "pgsql is more strict, increase the hackiness up to 11" :kca "KCA-55555" :state :resolved
               :merge-pull-request? false
               :commit-time #inst "2015-02-02"
               :html-url "http://www.google.com"
               :creation-time #inst "2015-01-01"}
              {:sha "03379467cafc13925eb18ddf0671e0c32949763d"
               :author "Jane Doe" :message "Obligatory placeholder commit message" :kca "KCA-66666" :state :dev-complete
               :merge-pull-request? false
               :commit-time #inst "2015-02-03"
               :html-url "http://www.google.com"
               :creation-time #inst "2015-01-01"}]}
   {:version "2000.1.3"
    :build-time #inst "2015-01-01"
    :creation-time #inst "2015-01-01"
    :commits [{:sha "44479467cafc13925eb18ddf0671e0c32949763d"
               :author "John Doe" :message "derp, helper method rename" :kca "KCA-77777" :state :resolved
               :merge-pull-request? false
               :commit-time #inst "2015-03-01"
               :html-url "http://www.google.com"
               :creation-time #inst "2015-01-01"}
              {:sha "55579467cafc13925eb18ddf0671e0c32949763d"
               :author "Jane Doe" :message "Just stop reading these for a while, ok" :kca "KCA-88888" :state :dev-complete
               :merge-pull-request? false
               :commit-time #inst "2015-03-02"
               :html-url "http://www.google.com"
               :creation-time #inst "2015-01-01"}
              {:sha "66679467cafc13925eb18ddf0671e0c32949763d"
               :author "Jane Doe" :message "Committing fixes in the dark, seriously, who killed my power!?" :kca "KCA-99999" :state :in-progress
               :merge-pull-request? false
               :commit-time #inst "2015-03-03"
               :html-url "http://www.google.com"
               :creation-time #inst "2015-01-01"}
              {:sha "77779467cafc13925eb18ddf0671e0c32949763d"
               :author "Jane Doe" :message "added test" :kca "KCA-99999" :state :qa-testing
               :merge-pull-request? false
               :commit-time #inst "2015-03-03"
               :html-url "http://www.google.com"
               :creation-time #inst "2015-01-01"}]}])

(defonce releases-atom (atom []))

(defn all [] releases-atom)

(swap! releases-atom
       #(update-in %
                   [0 :version]
                   (constantly "HHHHHHHHHHHHHHHHHHH")))

(swap! releases-atom
       (fn [v]
         (update-in v [0 :version] (constantly "sf"))))

(update-in [1 2 {:a 1}] [2 :a]
           (constantly 100))

(defn fetch []
  (reset! releases-atom (sort-by :version > releases)))







