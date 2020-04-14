import Vue from 'vue'
import VueResource from 'vue-resource'
import Vuetify from "vuetify";
import App from '../pages/App.vue'
import {connect} from "../pages/util/ws";
import 'vuetify/dist/vuetify.min.css'

if (frontendData.profile) {
    connect();
}

Vue.use(Vuetify);
// export default new Vuetify({ })
Vue.use(VueResource);

new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    render: a => a(App)
});