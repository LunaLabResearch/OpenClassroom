<script lang="ts">
	import { PUBLIC_API_BASE_URL } from '$env/static/public';
	import { onMount } from 'svelte';

	let classes: string[] = $state([]);

	async function getClasses() {
		const res = await fetch(`${PUBLIC_API_BASE_URL}/classes/`, {
			headers: {
				Authorization: `Bearer ${localStorage.getItem('accessToken')}`
			}
		});
		classes = await res.json();
	}

	onMount(() => getClasses());
</script>

<button
	onclick={async () => {
		const res = await fetch(`${PUBLIC_API_BASE_URL}/subjects/`, {
			headers: {
				Authorization: `Bearer ${localStorage.getItem('accessToken')}`
			}
		});
		const subjects = await res.json();
		console.log(subjects);
	}}>Get subjects</button
>

{#each classes as classroom}
	<p>{JSON.stringify(classroom)}</p>
{/each}
