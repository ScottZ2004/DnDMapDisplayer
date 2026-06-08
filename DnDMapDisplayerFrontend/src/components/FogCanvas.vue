<template>
  <canvas
      ref="canvasRef"
      class="absolute inset-0 w-full h-full"
      :class="mode === 'draw' ? '' : 'pointer-events-none'"
      @pointerdown="start"
      @pointermove="move"
      @pointerup="stop"
  />
</template>

<script setup lang="ts">
import { useFog } from "../composables/useFog";
import {onMounted} from "vue";

const props = defineProps<{
  mode: "pan" | "draw";
  scale: number;
}>();

const fog = useFog();

function start(e: PointerEvent) {
  if (props.mode !== "draw") return;
  move(e);
}

function move(e: PointerEvent) {
  if (props.mode !== "draw") return;

  const rect = fog.canvasRef.value!.getBoundingClientRect();

  const x = (e.clientX - rect.left) / props.scale;
  const y = (e.clientY - rect.top) / props.scale;

  fog.erase(x, y, 30 / props.scale);
}

function stop() {}

onMounted(fog.init);
</script>