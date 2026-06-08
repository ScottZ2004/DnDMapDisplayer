import {defineStore} from 'pinia';

export const useTestStore = defineStore('testStore', {
    state: () => ({
        count: 0
    }),
    actions: {
        increment() {
            this.count++;
        }
    }
});