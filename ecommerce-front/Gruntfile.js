module.exports = function(grunt) {

  grunt.initConfig({
    connect: {
      server: {
        options: {
	  port: 9001,
	  base: '.',
	  keepalive: true
	}
      }
    }
  });

  grunt.loadNpmTasks('grunt-contrib-connect');
  grunt.registerTask('default', ['connect']);
}
