<template>
  <div class="container">
    <h1>Medical System Frontend</h1>
    <nav>
      <router-link to="/patients">Patients</router-link> |
      <router-link to="/examinations">Examinations</router-link> |
      <router-link to="/appointments">Appointments</router-link> |
      <router-link to="/medical-records">Medical Records</router-link> |
      <router-link to="/medications">Medications</router-link>
    </nav>
    <hr />
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style scoped>
/* Additional scoped styles if needed */
</style>
