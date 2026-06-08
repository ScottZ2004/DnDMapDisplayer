<script setup lang="ts">
import {onMounted, onUnmounted, ref} from 'vue';

const channel = new BroadcastChannel('dnd-map');

const temp = ref(0);

function handleMessage(event: MessageEvent) {

  if (event.data.type === 'place-token') {

    console.log(

        `Token op (${event.data.x}, ${event.data.y})`

    );
    temp.value++;
  }
}

onMounted(() => {

  channel.addEventListener('message', handleMessage);

});

onUnmounted(() => {

  channel.removeEventListener('message', handleMessage);

  channel.close();

});
</script>

<template>
  <p>Hier komt de kaart</p>
  playerMap: {{temp}}
  <div id="playerMap"></div>
</template>

<style scoped>

</style>