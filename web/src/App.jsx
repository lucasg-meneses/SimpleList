
import { Box, CssBaseline, AppBar, Toolbar, IconButton } from '@mui/material'
import { ThemeProvider, createTheme } from '@mui/material/styles';
import './App.css'
import { cyan } from '@mui/material/colors';

const darkTheme = createTheme({
  palette: {
    mode: 'dark',
  
  },
});

const lightTheme = createTheme({
  palette: {
    mode: 'light',
    primary: '#000000'

  },
});


function App() {
  return (
    <ThemeProvider theme={lightTheme}>
      <Box sx={{ display: 'flex' }}>
        <CssBaseline />
        <AppBar component="nav" >
          <Toolbar>

          </Toolbar>
        </AppBar>
      </Box>
    </ThemeProvider>

  )
}

export default App
