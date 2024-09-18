<script lang="ts">
	import { PUBLIC_API_BASE_URL } from '$env/static/public';
</script>

<button
	on:click={async () => {
		const res = await fetch(`${PUBLIC_API_BASE_URL}/subjects/`, {
			headers: {
				Authorization: `Bearer ${localStorage.getItem('accessToken')}`
			}
		});
		const subjects = await res.json();
		console.log(subjects);
	}}>Get subjects</button
>
