<template>
  <div class="absolute inset-0">
    <img
        class="w-full h-full object-cover select-none"
        :src="imageUrl"
    />

    <FogCanvas
        :mode="mode"
        :scale="scale"
    />
  </div>
</template>

<script setup lang="ts">
import FogCanvas from "./FogCanvas.vue";

defineProps<{
  imageUrl: string;
  mode: "pan" | "draw";
  scale: number;
}>();
</script>