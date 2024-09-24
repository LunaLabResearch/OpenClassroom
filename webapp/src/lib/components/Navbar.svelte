<script lang="ts">
	import { PUBLIC_API_BASE_URL } from '$env/static/public';
	import { createDialog, melt } from '@melt-ui/svelte';
	import { signIn } from '@auth/sveltekit/client';

	const {
		elements: { trigger, overlay, content, title, description, close, portalled },
		states: { open }
	} = createDialog();

	async function addCourse(event: SubmitEvent) {
		const form = event.target as HTMLFormElement;

		if (!form) return;

		const nameInput = form.querySelector('input[name="name"]');
		const subjectIdInput = form.querySelector('input[name="subjectId"]');
		const descriptionInput = form.querySelector('input[name="description"]');

		if (!nameInput || !subjectIdInput || !descriptionInput) return;

		const response = await fetch(`${PUBLIC_API_BASE_URL}/classes/`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
				Authorization: `Bearer ${localStorage.getItem('accessToken')}`
			},
			body: JSON.stringify({
				name: (nameInput as HTMLInputElement).value,
				subjectId: (subjectIdInput as HTMLInputElement).value,
				description: (descriptionInput as HTMLInputElement).value
			})
		});
	}
</script>

<nav class="header">
	<!-- <button use:melt={$trigger} class="trigger">New class</button>
	{#if $open}
		<div use:melt={$portalled}>
			<div use:melt={$overlay} class="overlay" />
			<div class="content" use:melt={$content}>
				<h2 use:melt={$title} class="title">Edit profile</h2>
				<p use:melt={$description} class="description">
					Make changes to your profile here. Click save when you're done.
				</p>
				<form on:submit|preventDefault={addCourse}>
					<fieldset>
						<label for="name">Name</label>
						<input type="text" id="name" name="name" />
					</fieldset>
					<fieldset>
						<label for="subjectId">Subject ID</label>
						<input type="number" id="subjectId" name="subjectId" />
					</fieldset>
					<fieldset>
						<label for="description">Description</label>
						<input type="text" id="description" name="description" />
					</fieldset>
					<div class="actions">
						<button use:melt={$close} class="secondary"> Cancel </button>
						<button class="primary" type="submit">Create class</button>
					</div>
				</form>

				<button use:melt={$close} aria-label="close" class="close"> X </button>
			</div>
		</div>
	{/if} -->
	<button on:click={() => signIn('keycloak')}> Sign In </button>
</nav>

<style>
	.header {
		display: flex;
		align-items: center;
		justify-content: flex-end;
		width: 100%;
	}
</style>
