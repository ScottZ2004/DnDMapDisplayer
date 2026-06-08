<script setup lang="ts">
import World from "@/components/World.vue";
import FogOfWarViewer from "@/components/FogOfWarViewer.vue";

const channel = new BroadcastChannel('dnd-map');

function sendMessage() {

  channel.postMessage({

    type: 'place-token',

    x: 100,

    y: 200

  });

}



</script>

<template>
  <FogOfWarViewer/>
</template>