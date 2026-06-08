import {createRouter, createWebHistory} from 'vue-router';
import HomeView from "@/views/HomeView.vue";
import PlayerMapView from "@/views/PlayerMapView.vue";


const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomeView
        },
        {
            path: '/player-map',
            name: 'player-map',
            component: PlayerMapView
        }
    ]
});

export default router;