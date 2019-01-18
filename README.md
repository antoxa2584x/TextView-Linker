[![](https://jitpack.io/v/antoxa2584x/TextView-Linker.svg)](https://jitpack.io/#antoxa2584x/TextView-Linker)

# TextView-Linker

Simple Android Kotlin lib to highligt links in TextView

## How to
To get a Git project into your build:

### Step 1. Add the JitPack repository to your build file

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
### Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.antoxa2584x:TextView-Linker:Tag'
	}

### Step 3. Span TextView
  ```
textView?.spanAllLinks(ContextCompat.getColor(this, R.color.highlighted_text_material_light)) {
            var webPage = Uri.parse(it)
  }
  ```
  
[![1d4e6bb190cffced0774e6d71755ca27.md.jpg](https://picua.org/images/2019/01/18/1d4e6bb190cffced0774e6d71755ca27.md.jpg)](https://picua.org/image/nIDQXM)
