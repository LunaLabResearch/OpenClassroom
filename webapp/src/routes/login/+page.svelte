<script lang="ts">
	import { goto } from '$app/navigation';

	import { PUBLIC_API_BASE_URL } from '$env/static/public';

	async function login(event: SubmitEvent) {
		const form = event.target as HTMLFormElement;

		if (!form) return;

		const emailInput = form.querySelector('input[name="email"]');
		const passwordInput = form.querySelector('input[name="password"]');

		if (!emailInput || !passwordInput) return;

		const response = await fetch(`${PUBLIC_API_BASE_URL}/auth/login`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				email: (emailInput as HTMLInputElement).value,
				password: (passwordInput as HTMLInputElement).value
			})
		});

		if (response.ok) {
			const { token } = await response.json();
			// TODO: Think of a proper storage later
			localStorage.setItem('accessToken', token);
			goto('/');
		} else {
			alert('Invalid credentials');
		}
	}
</script>

<form on:submit|preventDefault={login}>
	<label for="email">
		Email
		<input type="email" id="email" name="email" />
	</label>
	<label for="password">
		Password
		<input type="password" id="password" name="password" />
	</label>
	<button type="submit">Login</button>
</form>

<style>
	form {
		display: flex;
		flex-direction: column;
		align-items: center;
	}

	label {
		display: flex;
		margin-bottom: 1em;
	}

	label > input {
		margin-left: 0.5em;
	}

	button {
		margin-top: 1em;
	}
</style>
