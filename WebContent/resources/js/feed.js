
/* login/register */

function swapHeader(mode) {
	let regi = document.getElementById('register')
	let logi = document.getElementById('login')
	
	if (mode === 'register') {
		regi.style.display = 'block'
		logi.style.display = 'none'
			
	} else if (mode === 'login') {
		regi.style.display = 'none'		
		logi.style.display = 'block'
	}
}