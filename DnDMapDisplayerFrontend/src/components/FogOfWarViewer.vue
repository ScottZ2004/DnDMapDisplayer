<template>
  <div class="w-screen h-screen bg-black relative">

    <!-- UI -->
    <div class="absolute top-4 left-4 z-50 flex gap-2">
      <button @click="camera?.zoom(0.2)">+</button>
      <button @click="camera?.zoom(-0.2)">-</button>
      <button @click="setPan">🤚</button>
      <button @click="setDraw">✍️</button>
    </div>

    <Camera ref="camera">

      <World
          :imageUrl="imageUrl"
          :mode="mode"
          :scale="camera?.scale ?? 1"
      />

    </Camera>

  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import Camera from "./Camera.vue";
import World from "./World.vue";
import { useInputMode } from "../composables/useInputMode";

const imageUrl = "https://picsum.photos/1920/1080";

const camera = ref<any>(null);

const { mode, setPan, setDraw } = useInputMode();
</script>