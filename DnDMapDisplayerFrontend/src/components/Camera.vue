<template>
  <div
      class="w-full h-full"
      @pointerdown="startPan"
      @pointermove="pan"
      @pointerup="stopPan"
      @pointercancel="stopPan"
      @wheel.prevent="onWheel"
  >
    <div class="absolute inset-0 pointer-events-none" :style="transform">
      <slot />
    </div>
  </div>
</template>

<script setup lang="ts">
import { useCamera } from "../composables/useCamera";

const cam = useCamera();

function onWheel(e: WheelEvent) {
  cam.zoom(e.deltaY < 0 ? 0.2 : -0.2);
}

defineExpose(cam);
</script>