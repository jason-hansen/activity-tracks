import { createContext } from 'react'

const UserContext = createContext({
  athleteId: null,
  hasLoginError: false,
  stravaClick: () => null,
  spotifyClick: () => null,
  appleClick: () => null
})

export default UserContext