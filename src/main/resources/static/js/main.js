import Vue from 'vue'
import Vuetify from "vuetify";
import "../js/api/resources.js"
import App from './pages/App.vue'
import {connect} from "./util/ws";
import 'vuetify/dist/vuetify.min.css'

if (frontendData.profile) {
    connect();
}

Vue.use(Vuetify);

new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    render: a => a(App)
});