(ns analytics.services.contexts
  (:require [analytics.core :refer :all]
            [analytics.utils :as util]
            [conman.core :as conman])
  (:import  [java.util Date])
  (:gen-class))

(defn get-property-context [op-id]
  (db-get-property-context-by-op-id
    {:op_id op-id}))


(defn add-property-context!
  [op-id op-type site-id user-id session-id
   page event property-name property-value]
    (db-create-property-context!
      {:op_id op-id
       :op_type op-type
       :site_id site-id
       :user_id user-id
       :session_id session-id
       :page page
       :event event
       :property_name property-name
       :property_value property-value
       :created_at (Date.)}))


(defn get-location-context [op-id]
  (db-get-location-context-by-op-id
    {:op_id op-id}))


(defn add-location-context!
  [op-id op-type site-id user-id session-id
   page event latitude longitude country-code
   city postal-code organization]
    (db-create-location-context!
      {:op_id op-id
       :op_type op-type
       :site_id site-id
       :user_id user-id
       :session_id session-id
       :page page
       :event event
       :loc_latitude latitude
       :loc_longitude longitude
       :loc_country_code country-code
       :loc_city city
       :loc_postal_code postal-code
       :loc_organization organization
       :created_at (Date.)}))


(defn get-app-context [op-id]
  (db-get-app-context-by-op-id
    {:op_id op-id}))


(defn add-app-context!
  [op-id op-type site-id user-id session-id
   page event app-name app-version]
    (db-create-app-context!
      {:op_id op-id
       :op_type op-type
       :site_id site-id
       :user_id user-id
       :session_id session-id
       :page page
       :event event
       :app_name app-name
       :app_version app-version
       :created_at (Date.)}))


(defn get-campaign-context [op-id]
  (db-get-campaign-context-by-op-id
    {:op_id op-id}))


(defn add-campaign-context!
  [op-id op-type site-id user-id session-id
   page event campaign-name source medium
   term content]
    (db-create-campaign-context!
      {:op_id op-id
       :op_type op-type
       :site_id site-id
       :user_id user-id
       :session_id session-id
       :page page
       :event event
       :campaign_name campaign-name
       :campaign_source source
       :campaign_medium medium
       :campaign_term term
       :campaign_content content
       :created_at (Date.)}))


(defn get-device-context [op-id]
  (db-get-device-context-by-op-id
    {:op_id op-id}))


(defn add-device-context!
  [op-id op-type site-id user-id session-id
   page event device-id manufacturer model
   device-name]
    (db-create-device-context!
      {:op_id op-id
       :op_type op-type
       :site_id site-id
       :user_id user-id
       :session_id session-id
       :page page
       :event event
       :device_id device-id
       :device_manufacturer manufacturer
       :device_model model
       :device_name device-name
       :created_at (Date.)}))


(defn get-network-context [op-id]
  (db-get-network-context-by-op-id
    {:op_id op-id}))


(defn add-network-context!
  [op-id op-type site-id user-id session-id
   page event carrier isp bluetooth cellular wifi]
    (db-create-network-context!
      {:op_id op-id
       :op_type op-type
       :site_id site-id
       :user_id user-id
       :session_id session-id
       :page page
       :event event
       :network_carrier carrier
       :network_isp (if (true? isp) 1 0)
       :network_bluetooth (if (true? bluetooth) 1 0)
       :network_cellular (if (true? cellular) 1 0)
       :network_wifi (if (true? wifi) 1 0)
       :created_at (Date.)}))


(defn get-screen-context [op-id]
  (db-get-screen-context-by-op-id
    {:op_id op-id}))


(defn add-screen-context!
  [op-id op-type site-id user-id session-id
   page event height width]
    (db-create-screen-context!
      {:op_id op-id
       :op_type op-type
       :site_id site-id
       :user_id user-id
       :session_id session-id
       :page page
       :event event
       :screen_height height
       :screen_width width
       :screen_height_width (str height "x" width)
       :created_at (Date.)}))


(defn get-page-context [op-id]
  (db-get-page-context-by-op-id
    {:op_id op-id}))


(defn add-page-context!
  [op-id op-type site-id user-id session-id
   page event referrer-type referrer-url
   referrer-domain page-title page-url]
    (db-create-page-context!
      {:op_id op-id
       :op_type op-type
       :site_id site-id
       :user_id user-id
       :session_id session-id
       :page page
       :event event
       :referrer_type referrer-type
       :referrer_url referrer-url
       :referrer_domain referrer-domain
       :page_title page-title
       :page_url page-url
       :created_at (Date.)}))

